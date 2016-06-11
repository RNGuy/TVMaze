# TVMaze
Java API to access TV Maze's online API.
TV Maze API information can be found at http://www.tvmaze.com/api.

## To Do
Comments & JavaDoc still needs to be added

## Maven Dependencies
```
    <dependency>
      <groupId>org.glassfish.jersey.core</groupId>
      <artifactId>jersey-client</artifactId>
      <version>2.22.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.glassfish.jersey.media</groupId>
      <artifactId>jersey-media-moxy</artifactId>
      <version>2.22.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>com.fasterxml</groupId>
      <artifactId>jackson-xml-databind</artifactId>
      <version>0.6.2</version>
      <scope>compile</scope>
    </dependency>
```

## Other Dependencies
Joda Time - http://www.joda.org/joda-time/

Version 2.9 tested and confirmed to work.
    
## Sample Usage
### Getting a list of shows with a search string and displaying cast members
```
    TVMaze api = new TVMaze();
		List<Result<Show>> searchResults = api.searchShows("Silicon Valley");
		for(Result<Show> result : searchResults) {
			System.out.println(result.getItem().getName());
			for(CastMember castmember : api.getCastForShow(result.getItem())) {
				System.out.printf("\t%s as %s\n", 
					castmember.getPerson().getName(), 
					castmember.getCharacter().getName()
				);
			}
		}
```
Above code will yield the following output:
```
Silicon Valley
	Thomas Middleditch as Richard
	Zach Woods as Jared Dunn
	T.J. Miller as Erlich
	Martin Starr as Gilfoyle
	Kumail Nanjiani as Dinesh
	Matt Ross as Gavin Belson
	Josh Brener as Big Head
	Amanda Crew as Monica
	Jimmy O. Yang as Jian Yang
	Suzanne Cryer as Laurie Bream
	Christopher Evan Welch as Peter Gregory
Start-ups: Silicon Valley
```
Note that there is no cast for "Start-ups: Silicon Valley".

### Getting the first search result for a string with embedded next episode
```
		TVMaze api = new TVMaze();
		Show show = api.searchSingleShow("Silicon Valley", Embeddable.SHOW.NEXT_EPISODE);
		System.out.printf("%s - %s\n", show.getNetwork().getName(), show.getName());
		System.out.printf("\tNext Episode: %s - %s", 
			show.getNextepisode().getAirstamp().toDate(), 
			show.getNextepisode().getName()
		);
```
Above code will yield the following output:
```
HBO - Silicon Valley
	Next Episode: Sun Jun 12 22:00:00 EDT 2016 - Bachman's Earning's Over-ride
```
Note: Sample results were accurate as of June 11, 2016.
