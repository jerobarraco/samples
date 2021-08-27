function encodeC(c, count)
    return (count > 1) ? string(count) * c : c
end

function encode(s)
    isempty(s) && return ""

    o = ""
    cur = s[1]
    count = 0
    for c in s
        if c == cur
            count+=1
            continue
        end
        o *= encodeC(cur, count)

        cur = c
        count = 1
    end

    o *= encodeC(cur, count)
    return o
end

function decode(s)
    isempty(s) && return ""

    snum = ""
    o = ""
    for c in s
        if isnumeric(c)
            snum *= c
            continue
        end

        num = isempty(snum) ? 1 : parse(Int64, snum)

        o *= c^num
        snum = ""
    end

    return o
end
