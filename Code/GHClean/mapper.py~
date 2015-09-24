#!/usr/bin/python

import sys
import os

# input comes from STDIN (stream data that goes to the program)
for line in sys.stdin:
    
    # ignoring header
    if "`Year(created_at)`" in line:
        continue
    
    
    
    line = line.strip()
    l = line.split(",")
    
    tag = l[1].replace("\"","").strip().lower()
    year = l[3].replace("\"","").strip().lower()
    
    
   
    
    # output goes to STDOUT (stream data that the program writes)
    if len(l[1]) > 0:
    	print "%s,%d" %( year + "$$" + tag, 1 )
