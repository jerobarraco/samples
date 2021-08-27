"""
    count_nucleotides(strand)

The frequency of each nucleotide within `strand` as a dictionary.

Invalid strands raise a `DomainError`.

"""
function count_nucleotides(strand)
	result = Dict('A' => 0, 'C' => 0, 'G' => 0, 'T' => 0)
	for c in strand
		if !(c in "ACGT")
			throw(DomainError(strand))
		else
			result[c] += 1
		end
	end
	return result
end
