ALPHABET = "abcdefghijklmnopqrstuvwxyz"
ALPHABET_LEN = length(ALPHABET)

# Can also use:
# alphabet = 'a':'z'
# cipher = Dict(zip(alphabet, reverse(alphabet)))

function code_char(c::AbstractChar)
	isnumeric(c) && return c

	index = findfirst(lowercase(c), ALPHABET)
	# returning empty string for join
	index == nothing && return ""
	# +1 because julia defaults to 1-index
	nindex = ALPHABET_LEN - index + 1
	return ALPHABET[nindex]
end

function encode(input)
	ret = ""
	count = 0
	for c in input
		nc = code_char(c)
		if nc == "" continue end

		# adding the space before adding the next letter avoids adding one just at the end
		if count >= 5
			count = 0
			ret *= " "
		end
		ret *= nc
		count += 1
	end
	return ret
end

function decode(input)
	return join([code_char(c) for c in input])
end

