ALPHABET = "abcdefghijklmnopqrstuvwxyz"
ALPHABET_LEN = length(ALPHABET)
function rotate(num, c::AbstractChar)
    lc = lowercase(c)
    index = findfirst(lc, ALPHABET)
    index == nothing && return c

    nindex = mod1(index[1] + num, ALPHABET_LEN)
    nc = only(ALPHABET[nindex]) # only forces a char
    return c == lc ? nc : uppercase(nc)
end

function rotate(num, str::AbstractString)
    return isempty(str) ? str : join([rotate(num, c) for c in str])
end