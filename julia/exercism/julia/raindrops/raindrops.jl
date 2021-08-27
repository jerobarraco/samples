function raindrops(number)
	ret = ""
	number % 3 == 0 && (ret *= "Pling")
	number % 5 == 0 && (ret *= "Plang")
	number % 7 == 0 && (ret *= "Plong")
	return isempty(ret) ? string(number) : ret
end
