function reloadTimelineFrame() {
    const ifr = document.getElementById('timelineframe');
    console.log(ifr);
    ifr.contentWindow.location.reload(true);
}

window.addEventListener('load', function () {
    setInterval(reloadTimelineFrame, 1000);
});