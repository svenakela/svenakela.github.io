function goToAnchor(anchor) {
    document.getElementById(anchor).scrollIntoView();
}

document.querySelectorAll('[scrollTo]').forEach(element => {
    var insertThis = '<div class="scrolldown" onclick="goToAnchor(\'xxx\')">Scroll down<br>&#8675;</div>'
        .replace('xxx', element.getAttribute('scrollTo'));
    element.insertAdjacentHTML('beforeend', insertThis);
    // element.addEventListener('click', () => { goToAnchor(element.getAttribute('scrollTo')) });
});