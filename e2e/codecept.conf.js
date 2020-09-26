const {setHeadlessWhen} = require('@codeceptjs/configure');

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

exports.config = {
    tests: './*_test.js',
    output: './output',
    helpers: {
        Puppeteer: {
            url: 'http://localhost:9080/overflow',
            show: true,
            windowSize: '1200x900'
        }
    },
    include: {
        I: './steps_file.js'
    },
    bootstrap: null,
    mocha: {},
    name: 'e2e',
    translation: 'fr-FR',
    plugins: {
        retryFailedStep: {
            enabled: true
        },
        screenshotOnFail: {
            enabled: true
        },
        autoLogin: {
            enabled: true,
            saveToFile: true,
            users: {
                user: {
                    login: (I) => {
                        I.amOnPage('/login')
                        I.see('Connexion')
                        I.fillField('#lUsername', 'gil')
                        I.fillField('#lPassword', secret('gil'))
                        I.click('#loginBtn')
                        I.seeInCurrentUrl('/questions');
                        I.see('Questions')
                    },
                    check: (I) => {
                        I.amOnPage('/questions');
                        I.see('Questions')
                    }
                }
            }
        }
    }
}