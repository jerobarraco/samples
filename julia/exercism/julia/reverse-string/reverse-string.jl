using Unicode: graphemes

const TEST_GRAPHEMES = true

function myreverse(str)
	return join(reverse(collect(graphemes(str))))
end

# a cooler one
# myreverse(s) = s |> graphemes |> collect |> reverse |> join
