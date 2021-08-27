function isisogram(s)
	s = lowercase(s)
	# pipes (mario would love this)
	return s |>
 		collect |>
		a -> filter(c-> isletter(c), a) |>
		a -> string.(a) |>
	 	a -> map(c->count(c, s) <2, a) |>
		all

	# more pythonic, list comprehension
	return all(
		[count(string(c), s) < 2 for c in s if isletter(c)]
	)

	# standard
	for c in s
		isletter(c) || continue
		count(string(c), s) > 1 && return false
	end
	return true

end
