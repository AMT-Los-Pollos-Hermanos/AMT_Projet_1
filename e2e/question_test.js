Feature('question');

Scenario('test question servlet', (I) => {
    I.amOnPage('/question');
    I.see('Questions ?')
});
