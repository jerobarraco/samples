import Unicode.isuppercase

CALM = "Calm down, I know what I'm doing!"
WHOA = "Whoa, chill out!"
SURE = "Sure."
FINE = "Fine. Be that way!"
WEVER = "Whatever."
function bob(stimulus)
    stimulus = strip(stimulus)
    if isempty(stimulus) return FINE end

    isQuest = stimulus[end] == '?' # == last(stimulus)
    isYell = any(isletter, stimulus) && stimulus == uppercase(stimulus)

    if isQuest
        return isYell ? CALM : SURE
    elseif isYell
        return WHOA
    end
    return WEVER
end
