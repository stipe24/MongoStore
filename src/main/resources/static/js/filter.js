document.getElementById("filter-btn").addEventListener("click", function () {
    const div = document.getElementById("filter");
    if (div.style.display==="grid") {
        div.style.display="none";
    }
    else {
        div.style.display="grid";
    }
})


window.onload = function() {
    const brandsEl = document.getElementById("brands");
    const sizesEl = document.getElementById("sizes");
    let brands = getBrands();
    let sizes = getSizes();
    brands = brands.replaceAll("[", "").replaceAll("]", "").replaceAll("\"", "");
    let brandsArr = brands.split(",");
    sizes = sizes.replaceAll("[", "").replaceAll("]", "").replaceAll("\"", "");
    let sizesArr = sizes.split(",");
    brandsEl.innerHTML = setOptions(brandsArr);
    sizesEl.innerHTML = setOptions(sizesArr);
    onMainCategoryChanged();
};

function onMainCategoryChanged() {
    const categoryEl = document.getElementById("category");
    const mainCategoryEl = document.getElementById("mainCategory");
    const selected = mainCategoryEl.options[mainCategoryEl.selectedIndex].text;
    let categories = getCategories(selected);
    categories = categories.replaceAll("[", "").replaceAll("]", "").replaceAll("\"", "");
    let categoriesArr = categories.split(",");
    categoryEl.innerHTML = setOptions(categoriesArr);
}

function getCategories(value) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/categories?mainCategory=" + value, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function getSizes() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/sizes", false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function getBrands() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/brands", false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function setOptions(arr) {
    var options = "";
    for (let i=0; i<arr.length; i++) {
        options = options + "<option value=" + arr[i] + " >" + arr[i] +"</option>\n";
    }
    return options;
}