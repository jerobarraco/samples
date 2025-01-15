#coding: utf-8
import os
import sys

import amodem
import amodem.send, amodem.audio, amodem.config, amodem.main

import utils
class _Dummy:
    def __enter__(self):
        return self

    def __exit__(self, *args):
        pass

def main():
	bitrate = os.environ.get('BITRATE', 1) or 48
	config = amodem.config.bitrates.get(int(bitrate))

	interface = amodem.audio.Interface(config=config)
	interface.load('libportaudio.so')

	src = sys.stdin.buffer
	with interface:
		p = interface.player()
		# src=amodem.__main__.Compressor(src)

		amodem.main.send(config, src=src, dst=p, gain=1.0, extra_silence=0.0)
	# r = interface.recorder()
	# am_send.Sender()
	src.close()
	pass

if __name__ == "__main__":
	main()
