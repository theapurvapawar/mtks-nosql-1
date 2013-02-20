#Conflation
###Based on Porter's Stemming Algorithm 

>Conflates all possible words to its root/base form
>Conflates stop words to NULL
>Conflates one word at a time

>Conflation needs to be done at three places
>1.) After parsing HTML page and extracting title tag, tag content needs to be conflated
>2.) On new article upload, its title needs to be conflated before query processing
>3.) Search term string needs to be conflated before being sent for query processing

>Advantage - Less stress on search in inverted index due to reduction of no. of keys
