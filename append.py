#!/usr/bin/python

import os

header = "/*\n" + " * AMT : Project 1 - Overflow\n" + " * Authors : Gil Balsiger, Chris Barros, Julien Béguin & Gaëtan Daubresse\n" + " * Date : 24.10.2020\n" + " */\n\n"

stream = os.popen('find src/ -type f -iname "*.java"')
output = stream.read()

files = output.splitlines()

for f in files:
    fd = open(f, 'r+')
    content = fd.read()
    fd.seek(0)
    fd.write(header + content)
    fd.flush()
    fd.close()
    
