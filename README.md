# Scraping
 It is an web crawler app, that uses scraping techniques to extract the first 30 entries 
 from https://news.ycombinator.com/, the following datas, the title, number of the order, 
 the amount of comments and points for each entry.

# Tools
JSOUP is used, It's a Java HTML Parser library, for extracting and manipulating data.
more info: https://jsoup.org/
 
# Operations (scenarios)
Filter all entries with more than five words in the title ordered by amount of comments first.
Filter all entries with less than or equal to five words in the title ordered by points.
