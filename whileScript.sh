total=0
covered=0
percentage=0

while IFS="," read -r rec1 rec2; do  
    let total=$total+$rec1
    let covered=$covered+$rec2
    let total=$total+$rec2
done < <(cut -d "," -f4,5 ./reports/report.csv | tail -n +2)

percentage=$(awk -v total=$total -v covered=$covered 'BEGIN { print ((covered/total)*100)}')
awk -v percentage=$percentage 'BEGIN { printf("Code Coverage= %0.1f%%\n", percentage)}'
