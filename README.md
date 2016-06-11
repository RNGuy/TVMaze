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
    
## Sample Usage
Getting a list of shows with the search string "Silicon Valley" and displaying cast members.
```
    TVMaze api = new TVMaze();
		List<Result<Show>> searchResults = api.searchShows("Silicon Valley");
		for(Result<Show> result : searchResults) {
			System.out.println(result.getItem().getName());
			for(CastMember castmember : api.getCastForShow(result.getItem())) {
				System.out.printf("\t%s as %s\n", castmember.getPerson().getName(), castmember.getCharacter().getName());
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
