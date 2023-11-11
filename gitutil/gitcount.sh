# $1 = author
# $2 = java or js or whatever grep
# $3 = date since (dd-mm-yyyy)
if [ -z "$1" ]
 then
   echo "No author supplied"
   exit 1
fi
if [ -z "$2" ]
  then
    echo "No type supplied Ex: java, js, tsx"
    exit 1
fi
if [ -z "$3" ]
  then
    echo "No date since supplied Ex: 2023-09-21"
    exit 1
fi
git log --branches --no-merges --numstat --pretty="%H %as" --author="$1" --since="$3" \
| grep $2 | grep -v node_modules | grep -v json | grep -v public \
| awk 'NF==3 {plus+=$1; minus+=$2} END {printf("+%d, -%d\n", plus, minus)}'
