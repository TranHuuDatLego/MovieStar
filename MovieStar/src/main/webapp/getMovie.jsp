
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Movie Details</title>
</head>
<body>
    <h1>Movie List</h1>
    <c:forEach var="movie" items="${moviesList}">
        <div>
            <h2>${movie.name_movies}</h2>
            <p>Genre: ${movie.genre}</p>
            <p>Release Year: ${movie.release_year}</p>
            <p>Duration: ${movie.duration}</p>
            <p>Description: ${movie.description}</p>
        </div>
    </c:forEach>
</body>
</html>
