Feature('login');

Scenario('test correct login', ({ I }) => {
    I.amOnPage('/login')
    I.see('Connexion')
    I.fillField('#lUsername', 'gil')
    I.fillField('#lPassword', secret('gil'))
    I.click('#loginBtn')
    I.seeInCurrentUrl('/questions');
    I.see('Questions', 'h1')
});

Scenario('test incorrect login', ({ I }) => {
    I.amOnPage('/login')
    I.see('Connexion')
    I.fillField('#lUsername', 'gil')
    I.fillField('#lPassword', secret('abcd'))
    I.click('#loginBtn')
    I.seeInCurrentUrl('/login');
    I.see('Connexion')
    I.see('Nom d\'utilisateur ou mot de passe incorrect.')
});