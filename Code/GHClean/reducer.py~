#!/usr/bin/python

import sys

current_tag = None
current_sum = 0


# input comes from STDIN (stream data that goes to the program)
for line in sys.stdin:
    
    tag, count = line.strip().split(",")
    #tag = (year + "$" + tag ).title()
    try:
        count = int(count)
	
    except ValueError:
        continue
    
    if tag == current_tag:
        current_sum += count
	
	
    else:
        if current_tag:
            # output goes to STDOUT (stream data that the program writes)
            print "%s,%s,%d" %( current_tag.split("$$")[0],current_tag.split("$$")[1], current_sum)
        current_tag = tag
        current_sum = count
	
