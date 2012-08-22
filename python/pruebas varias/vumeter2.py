import wave
import audioop

w = wave.open("c:\\yui - again.wav",'r')

while True:
	print "\c" , "#"*int(audioop.rms(w.readframes(500),1)/500)