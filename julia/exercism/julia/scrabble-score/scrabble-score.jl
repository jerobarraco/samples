const POINTS = [
	"aeioulnrst",
	"dg",
	"bcmp",
	"fhvwy",
	"k",
	"",
	"",
	"jx",
	"",
	"qz"
]

function score(str)
	str = lowercase(str)
	s = 0
	for (i, line) in enumerate(POINTS)
		for c in line
			s += count(string(c), str)*i
		end
	end
	return s
end
