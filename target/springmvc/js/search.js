/**
 * Created by positif on 19/05/2017.
 */
var searchTitle = function (item, page) {
    $("#searchItem").val(item)
    window.searchForm.action='/searchTitle/'+page;
    window.searchForm.submit();
}

var searchBody = function (item, page) {
    $("#searchItem").val(item)
    window.searchForm.action='/searchBody/'+page;
    window.searchForm.submit();
}