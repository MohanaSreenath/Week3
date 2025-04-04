public class Movie {
    String title;
    String director;
    int yearOfRelease;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }
}

class MovieManagementSystem {
    private Movie head;
    private Movie tail;

    public void addMovieAtBeginning(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addMovieAtPosition(String title, String director, int yearOfRelease, double rating, int position) {
        if (position <= 0) {
            addMovieAtBeginning(title, director, yearOfRelease, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        Movie current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            addMovieAtEnd(title, director, yearOfRelease, rating);
        } else {
            newMovie.next = current.next;
            newMovie.prev = current;
            if (current.next != null) {
                current.next.prev = newMovie;
            }
            current.next = newMovie;
            if (newMovie.next == null) {
                tail = newMovie;
            }
        }
    }

    public void removeMovieByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public void searchByDirector(String director) {
        Movie current = head;
        while (current != null) {
            if (current.director.equals(director)) {
                System.out.println(current.title + " " + current.yearOfRelease + " " + current.rating);
            }
            current = current.next;
        }
    }

    public void searchByRating(double rating) {
        Movie current = head;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println(current.title + " " + current.director + " " + current.yearOfRelease);
            }
            current = current.next;
        }
    }

    public void displayForward() {
        Movie current = head;
        while (current != null) {
            System.out.println(current.title + " " + current.director + " " + current.yearOfRelease + " " + current.rating);
            current = current.next;
        }
    }

    public void displayReverse() {
        Movie current = tail;
        while (current != null) {
            System.out.println(current.title + " " + current.director + " " + current.yearOfRelease + " " + current.rating);
            current = current.prev;
        }
    }

    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                current.rating = newRating;
                return;
            }
            current = current.next;
        }
    }
}