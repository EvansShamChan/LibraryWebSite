changeSearchPlaceholder();

document.getElementById('author').addEventListener("click", changeSearchPlaceholder);
document.getElementById('bookName').addEventListener("click", changeSearchPlaceholder);
document.getElementById('publicationDate').addEventListener("click", changeSearchPlaceholder);

function changeSearchPlaceholder() {
    var author = document.getElementById('author');
    var bookName = document.getElementById('bookName');
    var publicationDate = document.getElementById('publicationDate');
    var searchField = document.getElementById('searchKey');

    if (author.checked) {
        searchField.placeholder = "Example: Джеймс Джойс, Джеймс, Джойс";
    } else if (bookName.checked) {
        searchField.placeholder = "Example: Гамлет, 1984";
    } else if (publicationDate.checked) {
        searchField.placeholder = "Example: 2000-2020, 2016, -2000, 2000-";
    }
}