COMP = Dict('A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A')

function t(c)
    return COMP[c]
end

function to_rna(dna)
    try
        return map(t, dna)
    catch
        throw(ErrorException(dna))
    end
end

