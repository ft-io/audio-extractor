class App {
    state = {
        search: '',
        error: ''
    }

    constructor($app) {
        this.$app = $app;
        this.render();
    }

    setState (nextState, postRender = false) {
        this.state = {
            ...this.state,
            ...nextState
        };
        if (postRender) {
            this.render();
        }
    }

    onDownload () {
//        const form = new FormData();
//
//        form.method = 'POST';
//        form.action = '/audio-extract';
//        form.append('url', this.state.search);

        const form = document.createElement('form');

        form.setAttribute('method', 'POST');
        form.setAttribute('action', '/api/video/audio-extract');
        form.setAttribute('url', this.state.search)

        document.body.appendChild(form);
        form.submit();
        document.body.removeChild(form);

        setState({search: ''});
    }

    handleSearchInput (evt) {
        this.setState({search: evt.target.value});
    }

    render () {
        const $formContainer = document.createElement('section');
        $formContainer.className = 'form-container';

        const $form = document.createElement('div');
        $form.className = 'form';

        const $input = document.createElement('input');
        $input.className = 'input';
        $input.placeholder = 'Url을 입력하세요.';
        $input.value = this.state.search;
        $input.addEventListener('input', this.handleSearchInput.bind(this));

        const $btn = document.createElement('button');
        $btn.className = 'btn';
        $btn.innerText = '다운로드';
        $btn.addEventListener('click', this.onDownload.bind(this))

        $form.appendChild($input);
        $form.appendChild($btn);

        $formContainer.appendChild($form);
        this.$app.appendChild($formContainer);

        const $history = document.createElement('section');
        $history.className = 'history';
        this.$app.appendChild($history);
    }
}

export default App;