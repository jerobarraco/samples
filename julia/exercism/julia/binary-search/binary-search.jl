
function binarysearch(list, target)
    isempty(list) && return 1:0

    s = 1
    e = length(list)
    while(s<e+1)
        m = s + div(e-s, 2)
        cur = list[m]

        if cur == target
            return m:m
        elseif cur < target
            s = m+1
        else
            e = m-1
        end
    end

    return s:e
end