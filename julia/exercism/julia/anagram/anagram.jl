function is_anagram(sub, can)
    return (sub != can) && (sort(collect(sub)) == sort(collect(can)))
end

function detect_anagrams(subject, candidates)
    return filter(c -> is_anagram(lowercase(c), lowercase(subject)), candidates)
end
