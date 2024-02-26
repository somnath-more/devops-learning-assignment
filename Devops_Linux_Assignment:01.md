### Problem :Look at the following command and explain what it does by going through the documentation of each of the individual commands. Use the tools you've learned in the previous lesson to figure out step by step what is happening in the following.


```
url -s http://public-dns.info/nameserver/br.csv | cut -d, -f1 | shuf | tail -n 50 | xargs -i timeout 1 ping -c1 -w 1 {} | grep "time=" | awk '{print substr($7, 6, length($7)) " " substr($4, 1, length($4) -1)}' | sort -n | awk '{print $2 " " $1 "ms"}' | head -n 10 

```

In the submission, explain how the each command, its options and complete command works. Also add a screenshot of the output(4 or 5 lines after running the command will be enough)


 ```
 Problem statement: is to fetch DNS server information for Brazil, randomly select 50 servers, ping each server once, extract and format the ping time results, and finally, present the top 10 servers with the lowest ping times in a human-readable format
  ```
**Step 1:** Storing information into  dns_info of br.csv file
![Alt text](<Screenshot from 2024-02-26 15-36-04.png>)
**Step 2:**
![Alt text](<Screenshot from 2024-02-26 15-35-23.png>)

- echo "$dns_info": Prints the content of the variable dns_info, which holds the output of the curl command (DNS information).
- cut -d, -f1: Uses cut to extract the first field (column) from each line using a comma (,) as the delimiter. This is often used to isolate specific information from CSV data.
- shuf: Randomly shuffles the lines of the input. In this case, it shuffles the list of DNS server names obtained from the previous step.
- tail -n 50: Selects the last 50 lines from the shuffled output. This reduces the number of DNS servers to the top 50 from the shuffled list.





**STEP : 3** Prints the content of the variable filtered_dns, which contains the list of 50 DNS server names.

![Alt text](<Screenshot from 2024-02-26 15-43-34.png>)

- xargs -i timeout 1 ping -c1 -w 1 {}: Uses xargs to execute the timeout 1 ping -c1 -w 1 command for each item (DNS server) in the list. Here's what each part does:

- xargs -i: Takes each item from the input and substitutes it for {} in the subsequent command.
timeout 1: Sets a timeout of 1 second for the following command.
- ping -c1 -w 1 {}: Pings the DNS server once with a timeout of 1 second.
- grep "time=": Filters the output of the ping command to include only the lines that contain the string "time=". This extracts the ping time information from the ping result


**Step 4**: ping results, extracts relevant information, sorts the results based on ping time

![Alt text](<Screenshot from 2024-02-26 15-47-08.png>)

- awk '{print substr($7, 6, length($7)) " " substr($4, 1, length($4) -1)}': Uses awk to process each line of the ping results. This command extracts relevant information from the ping output. Here's what each part does:

-  substr($7, 6, length($7)): Extracts a substring starting from the 6th character of the 7th column (field). This is used to get the actual ping time value.
- " ": Adds a space between the ping time value and the next extracted value.
substr($4, 1, length($4) -1): Extracts a substring from the 1st character to the length of the 4th column minus 1. This is used to get the IP address or DNS server name.
- sort -n: Sorts the lines in ascending numerical order based on the first column (ping time). This ensures that the results are presented in order of increasing ping times.

 - awk '{print $2 " " $1 "ms"}': Uses awk again to switch the columns and add "ms" at the end of each line. Here's what each part does:

- {print $2 " " $1 "ms"}: Prints the 2nd column (DNS server name or IP address), followed by a space, then the 1st column (ping time), and finally "ms" to denote milliseconds.



