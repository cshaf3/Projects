from datetime import datetime
n = int(input("Enter the number of dates (n): "))
for x in range(n):
#runs loop n times
    date = input("Enter a date: ")
    dt = datetime.strptime(date, '%m/%d/%Y')
    year = dt.year
    month = dt.month
    day = dt.day
    y = year-((14-month)//12)
    k = y+(y//4)-(y//100)+(y//400)
    m = month+12*((14-month)//12)-2
    d = (day+k+((31*m)//12))%7
    #assigns value to day, month, year
    if d == 0:
        print("Sunday\n")
    elif d == 1:
        print("Monday\n")
    elif d == 2:
        print("Tuesday\n")
    elif d == 3:
        print("Wednesday\n")
    elif d == 4:
        print("Thursday\n")
    elif d == 5:
        print("Friday\n")
    elif d == 6:
        print("Saturday\n")
    #if/elif statements prints day based on value of d';/
print("End of the program")
