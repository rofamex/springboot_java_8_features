Java 8 Features

1. Stream  
sequence of elements and supports different kind of operations  
* forEach  
loops over the stream elements, calling the supplied function on each element, it is a terminal operation  
* peek  
similiar to forEach, but it is an intermediate operation, so you can continue to use stream operations
* map  
produces a new stream after applying a function to each element of the original stream. e.g. turn list of id into list of Customers  
* collect  
its one of the common ways to get stuff out of the stream once we are done with all the processing  
* filter  
this produces a new stream that contains elements of the original stream that pass a given test  
* findFirst  
returns an Optional for the first entry in the stream; the Optional can, of course, be empty  
* toArray  
use when need to get an array out of the stream  
* flatMap  
flatten the data structure to simplify further operations. e.g. turn List<List<String>> into List<String>  

  

# Project Dependencies

## Developer Tools  
###### Spring Boot DevTools

## SQL  
###### Spring Data JPA  
###### MS SQL Server Driver  

## Web  
###### Spring Web  
