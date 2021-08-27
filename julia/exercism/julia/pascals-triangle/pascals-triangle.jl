# took inspiration from https://www.geeksforgeeks.org/python-program-to-print-pascals-triangle/

function row(i)
	i == 0 && return [] # empty
	i == 1 && return [1] # trivial
    i < 6 && return parse.(Int, collect(string(11^(i-1)))) # optimized

	# even though i could get the binomial like this, i feel in this case the
	# manual way is a bit more efficient since it uses the previous cell to calculate the new one
	# return [ binomial(i-1, j) for j in 0:i-1]
	ret = []
	# first element is always 1
    C = 1
    for j in 1:i
        # using Binomial Coefficient
		push!(ret, C)
		# next cell is calculated using the previous cell value
        C = div(C * (i - j), j)
	end
	return ret
end

function triangle(n)
	n < 0 && throw(DomainError(n))
	return [row(i) for i in 1:n]
end
