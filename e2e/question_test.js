Feature('question');

Scenario('test question servlet', (I) => {
    I.amOnPage('');
    I.see('Connexion')
    I.seeElement({name: 'password'})
    I.fillField('username', 'admin'); 
    I.fillField('password', 'admin'); 
    I.click('Login'); 
    I.see('Question list'); 
    // pause();
});
