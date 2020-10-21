Feature('answer');

Scenario('Answer with unlogged user', ({ I }) => {
    I.amOnPage('/question/9603ce27-77c2-43c1-8c58-e01ca3c07242')
    //Redirected when user not connected
    I.amOnPage('/login')
});

Before(({login}) => {
    login('user');
});

Scenario('Answer with logged user', ( {I, login} ) => {
    login('user');
    I.amOnPage('/question/6ec53ae2-1a39-4ab5-8291-bc664051b855')
    I.fillField('Rich Text Editor, main', 'tralalilalaaa')
    I.click('Ajouter')
    I.seeInCurrentUrl('/question/6ec53ae2-1a39-4ab5-8291-bc664051b855')
    I.see('tralalilalaaa')
});

Scenario('Answer with logged user', ( {I, login} ) => {
    login('user');
    I.amOnPage('/question/9603ce27-77c2-43c1-8c58-e01ca3c07242')
    I.fillField('Rich Text Editor, main', 'I dont understand this question')
    I.click('Ajouter')
    I.seeInCurrentUrl('/question/9603ce27-77c2-43c1-8c58-e01ca3c07242')
    I.see('I dont understand this question')
});

