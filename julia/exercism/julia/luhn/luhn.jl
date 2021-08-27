function luhn(str)
	nums = []
	try
		nums = parse.(Int, filter(x -> !isspace(x), collect(str)))
	catch
		return false
	end

	length(nums)<2 && return false

	# multiply 2nd number
	# reversed because luhn starts from the right
	nn = [v * mod1(i, 2) for (i, v) in enumerate(reverse(nums))]
	# adjust overflow
	nn = map((x) -> (x > 9 ? x-9 : x), nn)
	s = sum(nn)
	return s % 10 == 0
end