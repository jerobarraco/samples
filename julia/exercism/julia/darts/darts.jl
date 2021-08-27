function score(x, y)
    dist = hypot(x, y)
    dist > 10 && return 0
    dist > 5 && return 1
    dist > 1 && return 5
    return 10
end
