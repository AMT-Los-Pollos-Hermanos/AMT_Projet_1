const { setHeadlessWhen } = require('@codeceptjs/configure');

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

exports.config = {
  tests: './*_test.js',
  output: './output',
  helpers: {
    Playwright: {
      url: 'http://localhost:9080/overflow',
      show: true,
      browser: 'chromium'
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
            I.see('Nouvelle question')
          }
        }
      }
    },
    pauseOnFail: {},
    retryFailedStep: {
      enabled: true
    },
    tryTo: {
      enabled: true
    },
    screenshotOnFail: {
      enabled: true
    }
  }
}