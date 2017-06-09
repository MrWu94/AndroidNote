#!/bin/bash
if [ "$1" = "" ]
then
	git branch --set-upstream-to=origin/master master
	git pull

else
	git pull origin $1
fi