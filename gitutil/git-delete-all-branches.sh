#!/bin/bash

for i in `git branch -a | grep remote | grep -v HEAD | grep -v main`; do git branch -d ${i#remotes/origin/} $i; done
