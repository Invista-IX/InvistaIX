document.getElementById('imagemInput').addEventListener('change', function () {
    let file = this.files[0];
    let reader = new FileReader();

    reader.onload = function (event) {
        let base64String = event.target.result;
        let PlaceHolder = document.querySelectorAll("#imgPlaceHolder");
        PlaceHolder[0].style.display = 'none';
        PlaceHolder[1].style.display = 'none';
        let preview = document.getElementById('preview');
        preview.src = base64String;
        preview.style.display = 'block';
        document.getElementById('imagemBase64').value = base64String;
    };

    reader.readAsDataURL(file);
});