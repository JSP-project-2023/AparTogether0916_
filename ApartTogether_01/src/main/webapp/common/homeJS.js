function handleScroll() {
    const scrollPosition = window.scrollY; // 현재 스크롤 위치

    var opt = 1 - (0.1 * scrollPosition) * 0.01;
    $('img[id=scroll-img]').css("opacity", opt);
}

$(document).ready(function () {

    $(window).scroll(function () {
        handleScroll();
    });
});