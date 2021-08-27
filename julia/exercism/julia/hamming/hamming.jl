"Your optional docstring here"
function distance(a, b)
	al = length(a)
	bl = length(b)
	if al-bl != 0 
		throw(ArgumentError("Length is not the same"))
	end

	d = 0
	for i in 1:al
		if a[i] != b[i]
			d +=1
		end
	end

	return d
end

