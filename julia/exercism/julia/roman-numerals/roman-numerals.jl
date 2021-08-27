const UNITS = (
	("I", "V"),
	("X", "L"),
	("C", "D"),
	("M", ""),
	("", "")
)

function roman_digit(par::Tuple{Int64,Char})
	index, digit = par[1], parse(Int, par[2])
	u1, u5 = UNITS[index]
	nu1, nu5 = UNITS[index+1]

	digit < 4 && return u1^digit
	digit == 4 && return u1*u5
	digit == 5 && return u5
	digit < 9 && return u5*(u1^(digit-5))
	digit == 9 && return u1*nu1
	throw(DomainError(string(digit)))
end

function to_roman(number)
	number < 1 && throw(ErrorException(string(number)))
 	return number |> string |> reverse |> enumerate |> e -> roman_digit.(e) |> reverse |> join
end
