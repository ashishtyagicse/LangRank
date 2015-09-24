#!/usr/bin/python

import sys

current_tag = None
current_sum = 0
totalanswers = 0
totalviews = 0
totalscore = 0

# input comes from STDIN (stream data that goes to the program)
for line in sys.stdin:
    
    tag, count, answercount, viewcount,score = line.strip().split(",")
    #tag = (year + "$" + tag ).title()
    try:
        count = int(count)
	answercount = int(answercount)
	viewcount = int(viewcount)
	score = int(score)
	#year = int(year)
    except ValueError:
        continue
    
    if tag == current_tag:
        current_sum += count
	totalanswers += answercount
	totalviews += viewcount
	totalscore += score
	
    else:
        if current_tag:
            # output goes to STDOUT (stream data that the program writes)
            print "%s,%s,%d,%d,%d,%d" %( current_tag.split("$$")[0],current_tag.split("$$")[1], current_sum ,totalanswers, totalviews,totalscore)
        current_tag = tag
        current_sum = count
	totalanswers = answercount
	totalviews = viewcount
	totalscore = score
