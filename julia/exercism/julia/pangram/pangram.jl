alphabet="bcdefghijklmnopqrstuvwxyz"

"""
    ispangram(input)

Return `true` if `input` contains every alphabetic character (case insensitive).

"""
function ispangram(input)
	input = lowercase(input)
	for c in alphabet
		if !(c in input)
			return false
		end
	end
	return true
end

