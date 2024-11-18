package main

import (
	"encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
)

type MovieResponse struct {
	Title  string
	Year   int
	ImdbID string `json:"imdbID"`
}

// It's important to make the attributes visible outside the main package by starting their names with
// UPPER CASE letters

type MoviesResponse struct {
	Page       int             `json:"page"`
	PerPage    int             `json:"per_page"`
	Total      int             `json:"total"`
	TotalPages int             `json:"total_pages"`
	Data       []MovieResponse `json:"data"`
}

func countMovies(title string) int {
	resp, httpErr := http.Get(fmt.Sprintf("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=%v", title))
	if httpErr != nil {
		log.Fatal(httpErr)
	}
	defer resp.Body.Close()

	body, bodyErr := io.ReadAll(resp.Body)
	if bodyErr != nil {
		log.Fatal(bodyErr)
	}

	// var moviesResponse map[string]any
	var moviesResponse MoviesResponse
	// moviesResponse := MoviesResponse{}
	jsonUnmarshalErr := json.Unmarshal(body, &moviesResponse)
	if jsonUnmarshalErr != nil {
		log.Fatal(jsonUnmarshalErr)
	}

	return moviesResponse.Total
}

func main() {
	if countMovies("ab") != 15 {
		panic("")
	}

	if countMovies("star") != 1 {
		panic("")
	}
}
