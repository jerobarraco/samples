const APOS = '\''
function wordcount(sentence)
	# replace non alpha with underscore
	clean = c->(isletter(c) || isnumeric(c) || c== APOS ? c : ' ')
	# removes apostrophe on begin/end
	clean_apos = w->strip(w, (APOS,))
	not_empty = w->(!isempty(w))
	# returns the count of a word in an array of words
	count_word = (w, a)->(filter(fw->fw==w, a) |> length)
	# returns (word, count) for an array of words
	count_words = a->( map( w->(w, count_word(w, a)), a) )

	# using pipes
	return sentence |> lowercase |> collect |> a->map(clean, a) |>
 	join |> a->split(a, " ") |> a->map(clean_apos, a) |> a->filter(not_empty, a) |> count_words |> Dict

	# abusing pipes (just for fun and confusion, this is equivalent)
	return Dict( count_words( filter( not_empty, clean_apos.( split( clean.( sentence |> lowercase |> collect ) |> join, " ") ) ) ) )
end

# standard edition
function wordcount_std(sentence)
	# clear
    letters = collect(lowercase(sentence))
	for (i,c) in enumerate(letters)
		(isletter(c) || isnumeric(c) || c== APOS) && continue
		letters[i] = ' '
	end

	words = split(join(letters), " ")
	# clean apos
	words = [strip(w, (APOS,)) for w in words]
	res = Dict()
	for w in words
		isempty(w) && continue
		count = 0
	 	for w2 in words
			w2 == w || continue
			count +=1
		end
		res[w] = count
	end
	return res
end