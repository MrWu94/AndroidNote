#!/usr/bin/env python
# -*- coding: utf-8 -*-
print 'hello, world'
print '100+300=', 100 + 300
name = raw_input('please enter your name:')
print 'hello', name
a = 10
if a > 10:
    print a
else:
    print -a

print '测试中文'

classmate = ['hanshen', 'lisa', 'peter']
print classmate[0]
print classmate[1]
print classmate
classmate.append('rechard')
classmate.insert(1, 'tim')
classmate.pop(2);

t = ('tim', 'tumber')
print t

names = ['fang', 'jia', 'mimg']
for name in names:
    print name

currentPoint = (1, 2, 3)
for c in currentPoint:
    print c

sum = 0
for a in range(100):
    sum = sum + a
    print a

birth = int(raw_input('birth: '))

if birth < 2000:
    print '00前'
else:
    print '00后'

d = {'hansheng': 1, 'dongzhi': 20}
print d['hansheng']


def getCurrentTime(x):
    if x >= 0:
        return x
    else:
        return -x
