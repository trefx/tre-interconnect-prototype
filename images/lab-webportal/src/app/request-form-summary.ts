export class RequestFormSummary
{
    public id:          string;
    public title:       string;
    public summary:     string;
    public description: string;

    public constructor(id: string, title: string, summary: string, description: string)
    {
        this.id          = id;
        this.title       = title;
        this.summary     = summary;
        this.description = description;
    }
}
