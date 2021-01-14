function goToAnchor(anchor) {
    document.getElementById(anchor).scrollIntoView();
}

document.querySelectorAll('[scrollTo]').forEach(element => {
    var insertThis = '<div class="scrolldown" onclick="goToAnchor(\'zzz\')">Mer<br>&#8675;</div>'
        .replace('zzz', element.getAttribute('scrollTo'));
    element.insertAdjacentHTML('beforeend', insertThis);
});